package DiscordBot;
import java.io.*;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;


public class bot {

    public static Person readSerialized(String path) throws IOException{
        Person p = null;
        try{

            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream toReturn = new ObjectInputStream(fileIn);

            p = (Person)toReturn.readObject();
            toReturn.close();
            fileIn.close();

            return p;
        } catch (Exception ex){
            System.out.println("Can't read object");
            return null;
        }
    }
    public static String getToken(String fileName) throws IOException{

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String botToken = br.readLine();
        return botToken;
    }

    public static void main(String[] args) throws IOException
    {

        try{

            //Obtain bot token-----------------------------------------------------------------------
            String botToken = getToken("/home/zac/IdeaProjects/JavaBot/src/main/resources/credentials.txt");

            DiscordApi api = new DiscordApiBuilder().setToken(botToken).login().join();
            api.addMessageCreateListener(event -> {
                // Split user input into an array
                String[] userInput = event.getMessageContent().toLowerCase().split(" ");

                if (userInput[0].equals("!logcal")) {
                    try{
                        int i = Integer.parseInt(userInput[1]); // If this cannot be parsed into an int the bot will ask the user to input a int
                    }
                    catch (NumberFormatException e) {
                        event.getChannel().sendMessage("Make sure you input an integer value for the calorie count.");
                    }
                }else if (userInput[0].equals("!logpro")){

                }else if (userInput[0].equals("!logfat")){
                    try {
                        Person p = null;
                        Person readObj = readSerialized("/home/zac/IdeaProjects/JavaBot/BoulderLee.txt");
                        System.out.println(readObj.height);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (userInput[0].equals("!makeprofile")){

                    // Extract desired information from the user input array
                    try {
                        int age = Integer.parseInt(userInput[1]);
                        int height = Integer.parseInt(userInput[2]);
                        int weight = Integer.parseInt(userInput[3]);
                        int targetWeight = Integer.parseInt(userInput[4]);
                        Person newUser = new Person(age,height,weight,targetWeight, event.getMessageAuthor().getDisplayName());
                        String filename = newUser.name+".txt";
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);

                        out.writeObject(newUser);
                        out.close();
                        file.close();

                    } catch (NumberFormatException n){
                        event.getChannel().sendMessage("Make sure to adhere to the following format: \n \n" +
                                "!makeProfile `<age>, <height in cm>, <weight>, <target weight>`");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


            });
            }catch (IOException e){
        System.out.println("Could not retrieve bot credentials");
    }
    }
}
