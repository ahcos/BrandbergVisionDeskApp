package main;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;


public class ClassifierCore {

        private boolean personChecked;
        private boolean multiplePersonsChecked;
        private boolean animalChecked;
        private boolean personWithBowChecked;
        private float threshold;


        // default
        public ClassifierCore () {

        }

    /**
     * Constructor to be used in general. Constructs an ClassifierCore object that holds the selection the user makes,
     * so the classifier works more specific regarding the output.
     *
     * @param personChecked
     * @param multiplePersonsChecked
     * @param animalChecked
     * @param personWithBowChecked
     */
        public ClassifierCore(boolean personChecked, boolean multiplePersonsChecked,
                              boolean animalChecked, boolean personWithBowChecked,
                                float threshold) {
            this.personChecked = personChecked;
            this.multiplePersonsChecked = multiplePersonsChecked;
            this.animalChecked = animalChecked;
            this.personWithBowChecked = personWithBowChecked;
            this.threshold = threshold;
        }

    /**
     * With an image file, gives out a String based on the selection the user made in the Main GUI on percentage (slider)
     * and classes (Checkboxes). This is also the function that currently has to be modified in order to use another classifier.
     * For that, modify apiKey value of "options" object and addClassifierId of the "classifyOptions" object.
     * @param file
     * @return
     */

    public String classify(File file) {

            // set up the informations for the API

            IamOptions options = new IamOptions.Builder()
                    .apiKey("8llOmkkniMQf3GRa2O483drLJB7iqkz5WU6rPROpuYLW")
                    .build();

            VisualRecognition vrClient = new VisualRecognition("2018-07-01", options);


            final StringBuffer buffer = new StringBuffer();

            try {
                ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                        .imagesFile(file)
                        .threshold(threshold)
                        .owners(Arrays.asList("me"))
                        .addClassifierId("humanclassifier_2106950051")
                        .build();

                // submit the information to the server, classify the image

                ClassifiedImages output = vrClient.classify(classifyOptions).execute();

                // fill the results into a list

                List<ClassResult> classes = output.getImages().get(0).getClassifiers().get(0).getClasses();

                //filter out the classes that are not selected
                //there are two things to consider: threshold and checkboxes set
                // using bitshifting, an Integer is getting flagged based on the selections made
                // Integer would be flagged as "1000" if only personWithBowChecked was selected

                // this needs to be changed based on how the classifier is trained!!

                int flag = 0;
                if (personChecked) flag = 1;
                if (multiplePersonsChecked) flag |= 1<<1;
                if (animalChecked) flag |= 1<<2;
                if (personWithBowChecked) flag |= 1<<3;

                // split the list up so the results can get visualized
                for (ClassResult result : classes) {

                    String name = result.getClassName();
                    Float scoreFloat = result.getScore();

                    // if result score is below the threshold, skip this result and go to the next result in the list
                    if (scoreFloat < threshold)
                        continue;

                    // if the result reaches the threshold, check if result should be displayed
                    // if so, append it to the final String


                    else if (name.equals("person") && (flag & 0b0001) == 0b0001) {

                        scoreFloat = scoreFloat * 100;
                        String score = scoreFloat.toString();
                        buffer.append("Class: " + name + "\n"
                                + "Score: " + score + "% \n"
                                + "__________________________\n");
                    }
                    else if (name.equals("a group of people") && (flag & 0b0010) == 0b0010) {
                        scoreFloat = scoreFloat * 100;
                        String score = scoreFloat.toString();
                        buffer.append("Class: " + name + "\n"
                                + "Score: " + score + "% \n"
                                + "__________________________\n");
                    }
                    else if (name.equals("animal") && (flag & 0b0100) == 0b0100) {
                        scoreFloat = scoreFloat * 100;
                        String score = scoreFloat.toString();
                        buffer.append("Class: " + name + "\n"
                                + "Score: " + score + "% \n"
                                + "__________________________\n");
                    }
                    else if (name.equals("person holding a bow") && (flag & 0b1000) == 0b1000) {
                        scoreFloat = scoreFloat * 100;
                        String score = scoreFloat.toString();
                        buffer.append("Class: " + name + "\n"
                                + "Score: " + score + "% \n"
                                + "__________________________\n");
                    }

                }




            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }
            return buffer.toString();

        }

    }


