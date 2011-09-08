package cleancodematters.requestfactory.testutils;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class RequestFactoryHelperTest {

  private static class DummyServiceLocator implements ServiceLocator {
    @Override
    public Object getInstance( Class<?> clazz ) {
      return null;
    }
  }
  
  private interface TestService {
    void testMethod();
  }
  
  
  private interface TestRequestFactory extends RequestFactory {
    TestRequestContext context();
  }
  
  @Service(value = TestService.class, locator = DummyServiceLocator.class)
  private interface TestRequestContext extends RequestContext {
    Request<Void> testMethod();
  }
  
  @Test
  public void testServiceIsSame() {
    TestService service1 = RequestFactoryHelper.getService( TestService.class );
    TestService service2 = RequestFactoryHelper.getService( TestService.class );
    
    assertSame( service1, service2 );
  }
  
  @Test
  public void testMockIsInvoked() {
    TestService service = RequestFactoryHelper.getService( TestService.class );
    TestRequestFactory factory = RequestFactoryHelper.create( TestRequestFactory.class );
    
    factory.context().testMethod().fire();
    
    verify( service ).testMethod();
  }
  
  @Test
  @SuppressWarnings("unchecked")
  public void testCaptureResult() {
    Receiver<Object> receiver = mock( Receiver.class );
    Object expected = new Object();
    receiver.onSuccess( expected );
    
    Object returned = RequestFactoryHelper.captureResult( receiver );
    
    assertSame( expected, returned );
  }
  
}
