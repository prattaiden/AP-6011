//MELANIE PRETTYMAN

package Rooms;

import Game.Adventure;
import Items.Item;

public class ClassRoom extends Room{

    public ClassRoom() {
        super("MSD-Classroom", "A fluorescently lit classroom that smells of student tears and stale coffee.");
        Item keyCard = new Item( "Key-Card", "U of U student ID. To use type 'use Key-Card' ");
        items_.add( keyCard );
    }

    @Override
    public void playerEntered() {
            System.out.println( "As you step into the classroom, an unfamiliar figure stands at the podium, wearing an uncharacteristically stern expression.\n"
                    + "The person, who bears an uncanny resemblance to Dave, turns abruptly toward you. With a sly grin, they mutter, \n"
                 +"'Four hours of struggle? Brace for more. I won't be lending a hand! No extensions either. Your assignment's now due in 10 minutes!'\n"
                    + "It's clear – this must be Dave's mischievous evil twin! Your mission: uncover the real Dave and return him to the classroom to salvage your grades!\n" );

    }

    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // has Dave, use
        if( cmd.equals( "release" ) && attr.equals( "Dave") ) {

            boolean hasDave = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "Dave" ) ) {
                    hasDave = true;
                    break;
                }
            }
            if( hasDave ) {
                System.out.println( "Congratulations! With swift wit and teamwork, you successfully discerned the imposter and rescued the authentic Dave,\n" +
                        "restoring order to the classroom. Your adept problem-solving skills and determination have saved the day,\n"+
                        "securing your academic triumph and ensuring the assignment's timely submission. Well done, intrepid scholars!\n");
            }
            else {
                System.out.println( "Dave’s evil twin erupts with an evil laughter and says to you ‘You seek the authentic Dave? Ha! He's entangled in a web of server confusion.\n"
                        +"Without his insight, your plight shall persist. Find your way if you dare, for Dave's aid won't be found here!'\n" );
            }
            return true;
        }
        return false;
    }
}
