package DiscordBot;
import java.io.BufferedReader;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;


public class bot {

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

                } else if (userInput[0].equals("!makeprofile")){

                    // Extract desired information from the user input array
                    try {
                        int age = Integer.parseInt(userInput[1]);
                        int height = Integer.parseInt(userInput[2]);
                        int weight = Integer.parseInt(userInput[3]);
                        int targetWeight = Integer.parseInt(userInput[4]);
                        Person newUser = new Person(age,height,weight,targetWeight, event.getMessageAuthor().getDisplayName());
                        System.out.println(newUser.name);
                    } catch (NumberFormatException n){
                        event.getChannel().sendMessage("Make sure to adhere to the following format: \n \n" +
                                "!makeProfile `<age>, <height in cm>, <weight>, <target weight>`");
                    }

                }


            });
            }catch (IOException e){
        System.out.println("Could not retrieve bot credentials");
    }
    }
}
