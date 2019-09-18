package com.drone.delivery.run;

import com.drone.delivery.utils.fileReader;
import java.util.*;
import com.drone.delivery.utils.*;
import com.drone.delivery.config.*;
import com.drone.delivery.exception.*;


public class droneDelivery {

    public static void main(String[] args)throws Exception{
        droneDelivery dd = new droneDelivery();
        droneConfig dc = new droneConfig();
        dd.processCustOrders(dc.SRC_FILE_PATH);
    }

    /**
     This Method gets the customer order delivery  time and NPS
     */

    public static String[] processCustOrders(String srcFilePath)throws Exception  {
        try {
            fileWriter fw = new fileWriter();
            timestampManager tm = new timestampManager();
            droneConfig dc = new droneConfig();
            droneUtils du = new droneUtils();
            List<String> inlist = fileReader.readFile(srcFilePath);
            TreeMap<String, String> custOrders = new TreeMap<String, String>();
            TreeMap<String, Double> deliveryDistance = new TreeMap<String, Double>();
            LinkedList<Integer> diffList = new LinkedList<Integer>();
            if(inlist.size()>0) {
                for (String in : inlist) {
                    String[] inL = in.split(" ");
                    deliveryDistance.put(inL[0], du.findDistance(inL[1]));
                    custOrders.put(inL[0], inL[2]);
                }
                //Sorting using list
                LinkedList<Map.Entry<String, Double>> outputlist = new LinkedList<Map.Entry<String, Double>>(deliveryDistance.entrySet());
                Collections.sort(outputlist, new Comparator<Map.Entry<String, Double>>() {
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                System.out.println("customer orders: " + custOrders);
                Map<String, String> deliveryStartTime = new LinkedHashMap<String, String>();
                Map<String, String> deliveryEndTime = new LinkedHashMap<String, String>();
                String droneStart = dc.DRONE_START_TIME;
                String droneEndTime = dc.DRONE_END_TIME;
                String[] finalList = new String[custOrders.size() + 1];
                int i = 0;
                for (Map.Entry<String, Double> entry : outputlist) {
                    deliveryStartTime.put(entry.getKey(), droneStart);
                    String deliverytime = tm.findStartTime(droneStart, entry.getValue() * 2);
                    String deliveredtime = tm.findStartTime(droneStart, entry.getValue());
                    deliveryEndTime.put(entry.getKey(), deliveredtime);
                    droneStart = deliverytime;
                    int n = (int) tm.findDifference(custOrders.get(entry.getKey()), deliveredtime);
                    diffList.add(n);
                    if (tm.findDifference(deliveredtime, droneEndTime) < 0) {
                        break;
                    }
                    finalList[i] = entry.getKey().concat(" ").concat(deliveryStartTime.get(entry.getKey()));
                    i++;
                }
                System.out.println("delivery start time: " + deliveryStartTime);
                System.out.println("delivered time: " + deliveryEndTime);
                int NPS = du.findNPS(diffList);
                System.out.println("NPS: " + NPS);
                finalList[i] = "NPS ".concat(String.valueOf(NPS));
                fw.writeTofile(finalList, dc.TARGET_FILE_PATH);
                System.out.println("successfully delivered customer orders");

                return finalList;
            }else{
                String[] finalList = new String[1];
                finalList[1] = "NO ORDERS FOUND";
                System.out.println(" No Orders Notification: "+ finalList[1]);
                return finalList;
            }
        } catch (Exception ex ) {
            System.out.println("Error while processing customer order drone delivery");
            throw new droneDeliveryException( ex,droneConfig.DRONE_ERROR_CODE);

        }


    }

}
