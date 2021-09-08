
public class Dispatcher extends DispatcherBase {

    private final PlanePriorityQueue planePriorityQueue = new PlanePriorityQueue();

    @Override
    public int size() {
        return planePriorityQueue.getSize();
    }

    @Override
    public void addPlane(String planeNumber, String time) {
        planePriorityQueue.add(new Plane(planeNumber, time));
    }

    @Override
    public String allocateLandingSlot(String currentTime) {
        Plane plane = planePriorityQueue.min();
        if (plane != null) {

            int currentTimeMinutes = PlanePriorityQueue.getMinutesFromTime(currentTime);
            int planeArrivalTime = PlanePriorityQueue.getMinutesFromTime(plane.getTime());
            int minutesDiff = currentTimeMinutes - planeArrivalTime;

            if (minutesDiff <= 5) {
                Plane planeRemoved = planePriorityQueue.removeMinPlane();
                if (planeRemoved != null) {
                    return planeRemoved.getPlaneNumber();
                }
            }
        }

        return null;
    }

    @Override
    public String emergencyLanding(String planeNumber) {
        Plane plane = planePriorityQueue.removePlane(planeNumber);
        if (plane != null) {
            return plane.getPlaneNumber();
        }

        return null;
    }

    @Override
    public boolean isPresent(String planeNumber) {
        return planePriorityQueue.contains(planeNumber);
    }

    /* Implement all the necessary methods of Dispatcher here */
}

/* Add any additional helper classes here */

class PlanePriorityQueue {
    private PlaneLinkedListNode head;
    private int size;

    public PlanePriorityQueue () {
        size = 0;
        head = null;
    }

    public void add(Plane plane) {
        PlaneLinkedListNode newNode = new PlaneLinkedListNode(plane);
        newNode.next = null;

        if (head == null) {
            head = newNode;
        } else {
            PlaneLinkedListNode current = head;
            PlaneLinkedListNode previous = null;

            while(current != null) {
                previous = current;

                // Found place somewhere in the center
                if (newNode.plane.compareTo(current.plane) <= 0 && current.next != null &&
                        newNode.plane.compareTo(current.next.plane) <= 0) {
                    PlaneLinkedListNode after = current.next;
                    current.next = newNode;
                    newNode.next = after;
                    break;
                }

                current = current.next;
            }

            // Not place found, put at the end
            if (current == null) {
                previous.next = newNode;
            }
        }

        size++;
    }

    public Plane removePlane(String planeNumber) {
        PlaneLinkedListNode current = head;

        // If at head
        if (current.plane.getPlaneNumber().equals(planeNumber)) {
            head = head.next;
            size--;
        }

        // the other cases
        PlaneLinkedListNode previous = current;
        Plane planeFound = null;
        current = current.next;
        while (current != null) {
            if (current.plane.getPlaneNumber().equals(planeNumber)) {
                previous.next = current.next;
                planeFound = current.getPlane();
            }
            previous = current;
            current = current.next;
        }

        if (planeFound != null) {
            size--;
        }

        return planeFound;
    }

    boolean contains(String planeNumber) {
        PlaneLinkedListNode current = head;
        while(current != null) {
            if(current.plane.getPlaneNumber().equals(planeNumber)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public Plane removeMinPlane() {
        if (head != null) {
            Plane plane = head.plane;
            head = head.next;
            return plane;
        }

        return null;
    }

    Plane min() {
        if (head != null) {
            return head.plane;
        }

        return null;
    }

    public int getSize() {
        return size;
    }

 /*   *//**
     * Helper method for testing
     *
     * @return array of planes sorted
     *//*
    @Override
    public String toString() {
        Plane[] result = new Plane[size];
        PlaneLinkedListNode current = head;
        int i = 0;
        while(current != null) {
            result[i] = current.plane;
            current = current.next;
            i++;
        }

        return Arrays.toString(result);
    }*/


    static class PlaneLinkedListNode {
        PlaneLinkedListNode next;
        Plane plane;

        public PlaneLinkedListNode(Plane plane) {
            this.plane = plane;
        }

        public PlaneLinkedListNode getNext() {
            return next;
        }

        public void setNext(PlaneLinkedListNode node) {
            this.next = node;
        }

        public Plane getPlane() {
            return plane;
        }
    }

    public static int getMinutesFromTime(String time) {
        return Integer.parseInt(time.substring(3)); // Runs O(2) -> O(1) size is fixed
    }
}