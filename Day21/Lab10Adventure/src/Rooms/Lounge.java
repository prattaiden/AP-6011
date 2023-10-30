//AIDEN PRATT

package Rooms;

import Game.Adventure;
import Rooms.Room;
import Items.Item;

public class Lounge extends Room {

    private boolean locked_ = true;

    public Lounge() {
        super("Lounge", "A small, dark room.");

        Item dave = new Item("Dave", "A petrified professor. to use type 'release Dave'.");
        items_.add(dave);
        Item plaque = new Item("Plaque", "Lounge created by Aiden Pratt. MSD-Classroom created by Melanie Prettyman." +
                "To win, enter the MSD-Classroom and pick up the Key-Card item. Leave the MSD-Classroom and enter the Lounge." +
                "Get the item Dave. you must use the Key-Card in the lounge in order to unlock the door and leave. then return to the " +
                "classroom and 'release Dave'. " );
        items_.add(plaque);
    }


    @Override
    public void playerEntered() {
        System.out.println("It is dark, you hear a faint voice reciting pseudo code for a web server.");
    }

    @Override
    public Room goThroughDoor(int doorNum) {

        if( locked_ ) {
            System.out.println( "The door is locked!" );
            return null;
        }
        else {
            return super.goThroughDoor( doorNum );
        }
    }

    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // unlock, use
        if( cmd.equals( "use" ) && attr.equals( "Key-Card") ) {

            boolean hasKeyCard = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "Key-Card" ) ) {
                    hasKeyCard = true;
                    break;
                }
            }
            if( hasKeyCard ) {
                System.out.println( "The lounge is now unlocked!");
                locked_ = false;
            }
            else {
                System.out.println( "You don't have a key card. you are stuck in the lounge." );
            }
            return true;
        }
//        if(cmd.equals("read") && attr.equals("plaque")){
//            System.out.println("INFO");
//        }
        return false;

    }




}