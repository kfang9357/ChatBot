import java.util.Scanner;
import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Kelly Fang
 * @version September 2018
 */
public class ChatBot3 {

    int soupCount = 0;
    int dishesCount = 0;
    int dessertCount = 0;
    String testItem = "";

    boolean foundItem = false;

    String[] dessertOfTheNight = {"Chocolate Soufle",
            "Fruit Tart",
            "Macaroons",
            "Tiramisu",
            "Cake",
            "Ice cream",
    };

    String[] soupOfTheDay = {"Clam Chowder",
            "Tomato Soup",
            "French Onion Soup",
            "Chicken Noodle Soup",
            "Lentil Soup",
            "Cauliflower Soup",
    };

    String[] chefSpecial = {"Filet Mignon",
            "Beef Wellington",
            "Butter Chicken",
            "Stuffed Pork Tenderloins",
            "Grilled Salmon",
    };

    String[] completeMenu = {"Clam Chowder",
            "Tomato Soup",
            "French Onion Soup",
            "Chicken Noodle Soup",
            "Lentil Soup",
            "Cauliflower Soup",
            "Beef Wellington",
            "Butter Chicken",
            "Stuffed Pork Tenderloins",
            "Grilled Salmon",
            "Fruit Tart",
            "Macaroons",
            "Tiramisu",
    };

    //String [] order = {};

    public void chatLoop(String statement) {
        Scanner in = new Scanner(System.in);
        System.out.println(getGreeting());


        while (!statement.equals("Bye")||!statement.equals("Goodbye")) {


            statement = in.nextLine();
            //getResponse handles the user reply
            System.out.println(getResponse(statement));


        }

        System.out.println("Come back soon!");

    }

    public String getGreeting() {
        return "Would you like to learn about the soup of the day, chef's special, or dessert of the night?";
    }

    public String getResponse(String statement) {
        String response = "";

        if (statement.length() == 0) {
            response = "Say something, please.";
        } else if (findKeyword(statement, "soup") >= 0) {
            System.out.print(getSoupOfTheDay(soupOfTheDay));
            if (statement.equalsIgnoreCase("yes")) {
                soupCount++;
            }

        } else if (findKeyword(statement, "chef's") >= 0) {
            System.out.println(getChefsSpecial(chefSpecial));
            if (statement.equalsIgnoreCase("yes")) {
                dishesCount++;
            }
        } else if (findKeyword(statement, "dessert") >= 0) {
            System.out.print(getDessertOfTheNight(dessertOfTheNight));
            if (statement.equalsIgnoreCase("yes")) {
                dessertCount++;
            }
        } else if (findKeyword(statement, "bill") >= 0) {
            response = getBill(dessertCount, soupCount, dishesCount);
        }

        // Response transforming I want to statement

        else if (findKeyword(statement, "How is the", 0) >= 0) {
            if (foundItem == true)
            {
                response = transformHowIsThe(statement);
            }
            else if (foundItem == false)
            {
                response = "Sorry we do not have " + testItem+".";
            }
        }

        else if (findKeyword(statement, "Can I get the", 0) >= 0) {
            if (foundItem == true)
            {
                response = transformDoYouHave(statement);
            }
            else if (foundItem == false)
            {
                response = "Sorry we do not have " + testItem +".";
            }
        }

        return response;
    }


    private String transformHowIsThe(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "How is the", 0);
        String restOfStatement = statement.substring(psn + 11).trim();
        return "The " + restOfStatement + " is " + getCompliment();
    }

    private String transformDoYouHave(String statement) {
        //  Remove the final period, if there is one
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("?")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "Do you have", 0);
        String testItem = statement.substring(psn + 11).trim();

        if (foundItem == true) {
            return "We do have " + testItem + ".";
        } else {
            return "Sorry, we do not have " + testItem + ".";
        }

    }





    /**
     if (findOrder(String testItem, String[] completeMenu) == true) {
     System.out.println("We do have " + testItem + ".");
     } else {
     System.out.println("Sorry, we do not have " + testItem + ".");
     }
     **/



    //private String

    private int findKeyword(String statement, String goal,
                            int startPos)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                    // letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }


    private String getSoupOfTheDay(String[]soupOfTheDay)
    {
            Random r = new Random ();
            return "The soup of the day is " + soupOfTheDay [r.nextInt(soupOfTheDay.length)];
    }

    private String getChefsSpecial(String[]chefSpecial)
    {
        Random r = new Random ();
        return "The chef's special is " + chefSpecial[r.nextInt(chefSpecial.length)];
    }

    private String getDessertOfTheNight(String[] dessertOfTheNight)
    {
            Random r = new Random ();
            return "The dessert of the night is " + dessertOfTheNight [r.nextInt(dessertOfTheNight.length)];
    }


    private String getRandomResponses()
    {
            Random r = new Random ();
            return randomResponses [r.nextInt(randomResponses.length)];
    }

    private String [] randomResponses = {"Can I get you anything else?",
            "Is there anything else you want?",
            "How else can I help you?",
    };

    private String getBill(int dessertCount, int soupCount, int dishesCount)
    {
        int total = (dessertCount*8) + (dishesCount*12) + (soupCount*9);
        return "You've ordered: " + soupCount + " soups, " + dishesCount + " dishes, and " + dessertCount + " desserts. Your total is " + total + " dollars.";
    }

    private String getCompliment()
    {
            Random r = new Random ();
            return compliment [r.nextInt(compliment.length)];
    }

    private String[]compliment = {"very good.",
            "delicious.",
            "excellent.",
            "well made.",
    };

    private void findOrder(String testItem, String[]completeMenu)
    {

        for (int i = 0; i >= completeMenu.length; i++) {
            if (testItem.equalsIgnoreCase(completeMenu[i])) {
                foundItem = true;
            } else {
                foundItem = false;
            }
        }
    }
}