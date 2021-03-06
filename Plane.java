public class Plane extends PlaneBase {

    public Plane(String planeNumber, String time) {
        super(planeNumber, time);
    }

    @Override
    public int compareTo(PlaneBase o) {

        // If the two planes share the same time then compare by alphabetical order
        if (this.getTime().equals(o.getTime())) {
            return this.getPlaneNumber().compareTo(o.getPlaneNumber());
        }

        // Otherwise if the two planes
        int otherPlaneTime = Integer.parseInt(o.getTime().replace(":", ""));
        int thisPlaneTime = Integer.parseInt(this.getTime().replace(":", ""));

        return thisPlaneTime - otherPlaneTime;
    }

    /* Implement all the necessary methods of Plane here */
}