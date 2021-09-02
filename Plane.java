public class Plane extends PlaneBase {

    public Plane(String planeNumber, String time) {
        super(planeNumber, time);
    }

    @Override
    public int compareTo(PlaneBase o) {

        // If the two planes share the same time then compare by alphabetical order
        if (this.getTime().equals(o.getTime())) {
            return this.getPlaneNumber().substring(0, 3).compareTo(o.getPlaneNumber().substring(0,3));
        }

        // Otherwise if the two planes
        Integer otherPlaneTime = Integer.valueOf(o.getTime().replace(":", ""));
        Integer thisPlaneTime = Integer.valueOf(this.getTime().replace(":", ""));

        return thisPlaneTime.compareTo(otherPlaneTime);
    }

    /* Implement all the necessary methods of Plane here */
}
