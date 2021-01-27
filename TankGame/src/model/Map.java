
package model;


public class Map {

        private MyPoint dimension;
        private final int grid = 16;

        public Map(int p1, int p2) {
            MyPoint point = new MyPoint(p1, p2);
            dimension = point;
        }

        public int getGrid() {
            return grid;
        }

        public MyPoint getDimension() {
            return dimension;
        }

        @Override
        public String toString() {
            return "map{" + "XY= " + dimension.getXY() + ", grid=" + grid + '}';
        }
    }