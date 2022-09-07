import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      System.out.println("Enter your input: ");
      String input = sc.nextLine();
        final String regex = "(^[7][0][8][0][(1-9)]$)| (^[7][0][8][9][(1-8)]$)|(^[7][0][8][1][(0-6)]$)| (^[7][0][8][2][(0-3)]$)|(^[7][0][8][3][(5-6)]$)| (^[7][0][8][7][(3-4)]$)";
       
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);
        
        if(matcher.find()) {
         System.out.println(input +" Valid Baton Rouge zip code");
      }
      else {
         System.out.println(input + " Zip code not in Baton Rouge");
            }
        }
    }
