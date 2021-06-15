package test.com.ltp.multithreading.entity.state;

import com.ltp.multithreading.entity.Car;
import com.ltp.multithreading.entity.state.TransportedState;
import com.ltp.multithreading.generator.CarGenerator;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CarStateTest {

    private Car mock;

    @BeforeMethod
    public void init(){
        mock = CarGenerator.generate(100, "test");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void nextExceptionTest(){
        mock.getCarState().next(mock);
        mock.getCarState().next(mock);
        mock.getCarState().next(mock);
    }

    @Test
    public void nextTest(){
        mock.getCarState().next(mock);
        mock.getCarState().next(mock);
        AssertJUnit.assertTrue(mock.getCarState() instanceof TransportedState);
    }

    @AfterMethod
    public void terminate(){
        mock = null;
    }

}
