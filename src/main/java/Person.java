import java.util.ArrayList;

public class Person {
    int height; // cm
    int weight; // LBS
    int userID;
    String name;
    int goal; // LBS

    ArrayList<Integer> weightHistory = new ArrayList<Integer>(); // Create an ArrayList object
    boolean gain; // Aiming to gain or lose weight?

    // Creates a personal profile for the user
    public Person(int height, int weight, int goal, String name){
        this.name = name;
        // Determine if the user wants to gain or lose weight
        if (weight < goal)
            this.gain = true;
        else
            this.gain = false;

        this.height = height;
        this.weight = weight;
//        this.weightHistory.
        this.goal = goal;
    }
}
