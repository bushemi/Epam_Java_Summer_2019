package com.epam.builder;

import com.epam.builder.houseParts.floors.ConcreteFloor;
import com.epam.builder.houseParts.floors.Floor;
import com.epam.builder.houseParts.roofs.Roof;
import com.epam.builder.houseParts.roofs.StrawRoof;
import com.epam.builder.houseParts.walls.Wall;
import com.epam.builder.houseParts.walls.WallWithDoor;
import com.epam.builder.houseParts.walls.WallWithWindow;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class HouseTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void buildDefaultHouse() {
        //GIVEN
        House.HouseBuilder houseBuilder = new House.HouseBuilder();

        //WHEN
        House house = houseBuilder.build();

        //THEN
        assertNotNull(house);
        assertNotNull(house.getBackWall());
        assertNotNull(house.getFloor());
        assertNotNull(house.getFrontWall());
        assertNotNull(house.getLeftWall());
        assertNotNull(house.getRightWall());
        assertNotNull(house.getRoof());
    }

    @Test
    public void buildHouse() {
        //GIVEN
        House.HouseBuilder houseBuilder = new House.HouseBuilder();

        Wall backWall = new WallWithDoor();
        Floor floor = new ConcreteFloor();
        Wall frontWall = new WallWithWindow();
        Wall leftWall = new WallWithWindow();
        Wall rightWall = new WallWithWindow();
        Roof roof = new StrawRoof();

        houseBuilder.setBackWall(backWall)
                .setFloor(floor)
                .setFrontWall(frontWall)
                .setRightWall(rightWall)
                .setLeftWall(leftWall)
                .setRoof(roof);

        //WHEN
        House house = houseBuilder.build();

        //THEN
        assertNotNull(house);
        assertThat(house.getBackWall(), is(equalTo(backWall)));
        assertThat(house.getFloor(), is(equalTo(floor)));
        assertThat(house.getFrontWall(), is(equalTo(frontWall)));
        assertThat(house.getRightWall(), is(equalTo(rightWall)));
        assertThat(house.getLeftWall(), is(equalTo(leftWall)));
        assertThat(house.getRoof(), is(equalTo(roof)));
    }
}