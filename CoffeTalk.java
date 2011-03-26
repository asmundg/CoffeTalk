/**
* coffeetalk
*
* A simple program to randomly generate coffee bar drinks for those who
* want to hit a Starbucks over lunch but don't know what to buy.
* Keeps it simple but follows the standard call syntax. As written, it'll
* generate decaf and iced drinks at random; as my Perl capabilities
* develop, you'll be able to hack it around a bit.
*
* Read around the source a bit and you should be able to learn something
* about being a barista as well. I'd like to think it's almost as
* interesting as hacking Perl.
*
* coffeetalk is (c)1998 Brian Connors and is freely distributable under
* the terms of the GNU Public License. If you need a copy of the GPL,
* go to http://www.gnu.org or grep your distribution disks (you are using
* Linux, right? :-) ) for a file called COPYING.
*/
import java.util.Random;

public class CoffeTalk {
    /** There's always a stupid user. */
    private String usage = "usage: coffeetalk -dDiImM2bFu. RTFMP.";

    /**
     * The different drink components, arranged neatly in arrays. Yes,
     * it's a bit ugly, but it's Perl. You will see that on some
     * categories, options are repeated more than once. This is to
     * cause a statistical slant in favor of those items. Like I said,
     * ugly.
     */
    private String[] iced = {"hot", "hot", "hot", "hot", "iced"};
    private String[] caff = {"regular", "regular", "regular", "decaf",
                             "halfcaf"};
    //private String[] shots = {"single", "double", "triple", "quad"};

    /**
     * trivia: venti is Italian for twenty, and it's a twenty-ounce
     * cup.
     */
    private String[] size = {"short", "tall", "grande", "venti"};
    private String[] milk = {"whole", "whole", "whole", "whole", "breve", "2%",
                             "nonfat", "nonfat"};

    /**
     * This reflects the Starbucks flavor selection. It's rather more
     * limited than most coffee places I've seen; if you have your own
     * additions, feel free to add them.
     */
    private String[] flavor = {"plain", "plain", "plain", "plain", "plain",
                               "vanilla", "hazelnut", "almond", "irishcreme",
                               "raspberry", "mint", "valencia"};
    private String[] drink = {"espresso", "americano", "latte", "cappucino",
                              "mocha"};

    public static void main(final String[] args) {
        new CoffeTalk(args);
    }

    public CoffeTalk(final String[] args) {
        Random generator = new Random();
        /**
         * And now generate the drink...  This part prints nothing. It
         * simply generates a series of words from the above arrays,
         * creating the raw drink name. Of course, a venti skim
         * americano is nonsensical and a valencia espresso is
         * disgusting, but we'll deal with that later. Ah, the glories
         * of ad hoc programming...
         */
        String miced = iced[generator.nextInt(iced.length)];
        String mcaff = caff[generator.nextInt(caff.length)];
        //String mshots = shots[generator.nextInt(shots.length)];
        String msize = size[generator.nextInt(size.length)];
        String mmilk = milk[generator.nextInt(milk.length)];
        String mflavor = flavor[generator.nextInt(flavor.length)];
        String mdrink = drink[generator.nextInt(drink.length)];

        /**
         * There are a few command line args you might want to know
         * about. Like everything about this beast, it's an ad hoc
         * mess. But like I said, it's Perl.

         * -i and -I control iced drinks.
         * -d, -h, and -D control caffeination.
         * -s is meaningless at this point, but I may use it to control
         *    Starbucks-specific features.
         * -m will force whole milk; -M will force skim; -2 will force 2%.
         * -F will force non-flavored drinks. Due to the structure of the flavor
         *    generator, -f is currently not worth the trouble.

         * The interface is fairly primitive; I don't know much Perl
         * yet, so there's no way I'm screwing with getopt or
         * getopt_long.
         *
         * Of course, this is still pretty screwy.
         */

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                miced = "iced";
            } else if (args[i].equals("-I")) {
                miced = "hot";
            } else if (args[i].equals("-d")) {
                mcaff = "decaf";
            } else if (args[i].equals("-D")) {
                mcaff = "regular";
            } else if (args[i].equals("-h")) {
                mcaff = "halfcaf";
            } else if (args[i].equals("-b")) {
                mmilk = "breve";
            } else if (args[i].equals("-m")) {
                mmilk = "whole";
            } else if (args[i].equals("-M")) {
                mmilk = "nonfat";
            } else if (args[i].equals("-2")) {
                mmilk = "2%";
            } else if (args[i].equals("-F")) {
                mflavor = "plain";
            } else if (args[i].equals("-u")) {
                System.out.println(usage);
            }
        }

        /**
         * And now we untangle the mess and print it out.
         */

        /**
         * We'll see this again...
         */
        if (miced != "hot") {
            System.out.print(miced + " ");
        }

        if (mcaff != "regular") {
            System.out.print(mcaff + " ");
        }

        /**
         * The size logic is a mess because espresso uses different
         * terminology.
         */
        if (mdrink == "espresso") {
            if (msize == "short" || msize == "tall") {
                System.out.print("solo ");
            } else {
                System.out.print("doppio ");
            }
        } else {
            System.out.print(msize + " ");
        }

        /**
         * Interestingly, I once came up with a computer language idea
         * that had an unless clause in it
         * (http://www.cs.bc.edu/~connorbd/magenta.html).  Never
         * thought it would be the easiest way to do anything.
         */
        if (mdrink != "americano" && mdrink != "espresso") {
            if (mmilk != "whole") {
                System.out.print(mmilk + " ");
            }
        }

        if (mflavor != "plain") {
            System.out.print(mflavor + " ");
        }

        /**
         * Sometimes it's easy, though...
         */
        System.out.println(mdrink);

        /**
         * As stated in the docs, this stuff is GPL, but if there are any
         * attempts to expand this for a special purpose, I'd appreciate
         * knowing about them so I can distribute everything about them.
         * Some possible enhancements:
         *	-Additions of Seattlish stuff like Italian sodas
         *	-More Starbucks stuff
         *	-Versions specific to other coffee shops (I may release a
         *	 Coffee Connection version for nostalgic Bostonians if
         *	 there's any demand at all).
         * Let me know, and if you do hack it send me a copy so I can include
         * it in the distribution.
         */
    }
}
