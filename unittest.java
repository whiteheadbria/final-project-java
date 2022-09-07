import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        final String regex = "(^[7][0][8][0][(1-9)]$)| (^[7][0][8][9][(1-8)]$)|(^[7][0][8][1][(0-6)]$)| (^[7][0][8][2][(0-3)]$)|(^[7][0][8][3][(5-6)]$)| (^[7][0][8][7][(3-4)]$)";
        final String string = "70814";
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);
        
        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }
}
