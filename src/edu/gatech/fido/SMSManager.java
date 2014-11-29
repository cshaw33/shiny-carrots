package edu.gatech.fido;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import static edu.gatech.fido.Constants.SMS.*;

/**
 * Created by automation on 9/15/14.
 */
public class SMSManager extends BroadcastReceiver {
    private String lastMessage;
    private String lastCommand;

    public void onReceive(Context context, Intent intent) {
        String message = handleMessage(intent);
        lastMessage = message;
        System.out.println("Last message was... " + lastMessage);
        respondToMessage(message);
    }

    private void respondToMessage(String message) {
        if (isParrotMessage(message)) {
            message = message.replace(STARTCOMMAND, "");
        }
        else
        {
            return;
        }
        lastCommand = message;
        System.out.println("Last command was... " + lastCommand);

        String[] splitmessage = message.split(" ");
        String command = splitmessage[0];
        switch (command) {
            case GOTOGPS:
                DroneManager.goTo(splitmessage[1]);
                break;
            case TAKEOFF:
                DroneManager.takeOff();
                break;
            case LAND:
                    DroneManager.land();
            case GOFORWARD:
                DroneManager.forward(Integer.parseInt(splitmessage[1]));
                break;
            case GOBACK:
                DroneManager.back(Integer.parseInt(splitmessage[1]));
                break;
            case GOLEFT:
                DroneManager.left(Integer.parseInt(splitmessage[1]));
                break;
            case GORIGHT:
                DroneManager.right(Integer.parseInt(splitmessage[1]));
                break;
            case GOUP:
                DroneManager.up(Integer.parseInt(splitmessage[1]));
                break;
            case GODOWN:
                DroneManager.down(Integer.parseInt(splitmessage[1]));
                break;
            case TURNLEFT:
                DroneManager.turnCounterClockwise(Integer.parseInt(splitmessage[1]));
                break;
            case TURNRIGHT:
                DroneManager.turnClockwise(Integer.parseInt(splitmessage[1]));
                break;
            case EMERGENCY:
                DroneManager.emergency();
                break;
            case RESET:
                DroneManager.reset();
                break;
            case PREFLIGHT:
                DroneManager.preflightChecklist();
                break;
        }
    }

    private boolean isParrotMessage(String message) {
        return message.contains(STARTCOMMAND);
    }

    private String handleMessage(Intent intent) {
        Bundle messageBundle = intent.getExtras();
        SmsMessage[] messages;
        String finalMessage = "";
        {
            Object[] pdus = getProtocolDescriptionUnit(messageBundle);
            messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                finalMessage += messages[i].getMessageBody().toString();
            }
        }
        return finalMessage;
    }

    private Object[] getProtocolDescriptionUnit(Bundle bundle) {
        return (Object[]) bundle.get("pdus");
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastCommand() {
        return lastCommand;
    }
}
