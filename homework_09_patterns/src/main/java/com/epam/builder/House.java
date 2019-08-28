package com.epam.builder;

import com.epam.builder.houseParts.floors.Floor;
import com.epam.builder.houseParts.floors.WoodenFloor;
import com.epam.builder.houseParts.roofs.Roof;
import com.epam.builder.houseParts.roofs.SolarRoof;
import com.epam.builder.houseParts.walls.Wall;
import com.epam.builder.houseParts.walls.WallWithDoor;
import com.epam.builder.houseParts.walls.WallWithWindow;

public class House {

    private Roof roof;
    private Wall frontWall;
    private Wall leftWall;
    private Wall backWall;
    private Wall rightWall;
    private Floor floor;

    private void setRoof(Roof roof) {
        this.roof = roof;
    }

    private void setFrontWall(Wall frontWall) {
        this.frontWall = frontWall;
    }

    private void setLeftWall(Wall leftWall) {
        this.leftWall = leftWall;
    }

    private void setBackWall(Wall backWall) {
        this.backWall = backWall;
    }

    private void setRightWall(Wall rightWall) {
        this.rightWall = rightWall;
    }

    private void setFloor(Floor floor) {
        this.floor = floor;
    }


    public Roof getRoof() {
        return roof;
    }

    public Wall getFrontWall() {
        return frontWall;
    }

    public Wall getLeftWall() {
        return leftWall;
    }

    public Wall getBackWall() {
        return backWall;
    }

    public Wall getRightWall() {
        return rightWall;
    }

    public Floor getFloor() {
        return floor;
    }

    private House() {
    }

    public static class HouseBuilder {
        private static final SolarRoof DEFAULT_ROOF = new SolarRoof();
        private static final WallWithDoor DEFAULT_FRONT_WALL = new WallWithDoor();
        private static final WallWithWindow DEFAULT_SIDE_WALL = new WallWithWindow();
        private static final WoodenFloor DEFAULT_FLOOR = new WoodenFloor();

        private Roof roof = DEFAULT_ROOF;
        private Wall frontWall = DEFAULT_FRONT_WALL;
        private Wall leftWall = DEFAULT_SIDE_WALL;
        private Wall backWall = DEFAULT_SIDE_WALL;
        private Wall rightWall = DEFAULT_SIDE_WALL;
        private Floor floor = DEFAULT_FLOOR;

        public HouseBuilder() {

        }

        public HouseBuilder setRoof(Roof roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder setFrontWall(Wall frontWall) {
            this.frontWall = frontWall;
            return this;
        }

        public HouseBuilder setLeftWall(Wall leftWall) {
            this.leftWall = leftWall;
            return this;
        }

        public HouseBuilder setBackWall(Wall backWall) {
            this.backWall = backWall;
            return this;
        }

        public HouseBuilder setRightWall(Wall rightWall) {
            this.rightWall = rightWall;
            return this;
        }

        public HouseBuilder setFloor(Floor floor) {
            this.floor = floor;
            return this;
        }

        public House build() {
            House house = new House();
            house.setRoof(roof);
            house.setFrontWall(frontWall);
            house.setLeftWall(leftWall);
            house.setBackWall(backWall);
            house.setRightWall(rightWall);
            house.setFloor(floor);
            return house;
        }
    }
}
