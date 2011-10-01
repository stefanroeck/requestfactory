package cleancodematters.requestfactory.polymorphism;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import cleancodematters.requestfactory.polymorphism.client.CarProxy;
import cleancodematters.requestfactory.polymorphism.client.TruckProxy;
import cleancodematters.requestfactory.polymorphism.client.VehicleProxy;
import cleancodematters.requestfactory.polymorphism.client.VehicleRequestFactory;
import cleancodematters.requestfactory.polymorphism.server.Car;
import cleancodematters.requestfactory.polymorphism.server.Truck;
import cleancodematters.requestfactory.polymorphism.server.Vehicle;
import cleancodematters.requestfactory.polymorphism.server.VehicleProvider;
import cleancodematters.requestfactory.testutils.RequestFactoryHelper;

import com.google.web.bindery.requestfactory.shared.Receiver;

public class VehicleRequestFactoryTest {

  private VehicleProvider service;
  private VehicleRequestFactory factory;

  @Before
  public void setup(){
    service = RequestFactoryHelper.getService( VehicleProvider.class );
    factory = RequestFactoryHelper.create( VehicleRequestFactory.class );
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Test
  public void testPolymorphism() {
    Vehicle vehicle = new Vehicle();
    Car car = new Car();
    Truck truck = new Truck();
    when( service.getListOfVehicles() ).thenReturn( Arrays.asList( vehicle, car, truck ) );
    Receiver<List<VehicleProxy>> receiver = mock( Receiver.class );
    
    factory.context().getListOfVehicles().fire( receiver );
    
    ArgumentCaptor<List<VehicleProxy>> captor = (ArgumentCaptor)ArgumentCaptor.forClass( List.class );
    verify( receiver ).onSuccess( captor.capture() );
    List<VehicleProxy> vehicleProxyList = captor.getValue();
    assertTrue( vehicleProxyList.get( 0 ) instanceof VehicleProxy );
    assertTrue( vehicleProxyList.get( 1 ) instanceof CarProxy );
    assertTrue( vehicleProxyList.get( 2 ) instanceof TruckProxy );
  }
  
}
