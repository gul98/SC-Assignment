package App;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by gulza on 22/05/2017.
 */
@RunWith(Arquillian.class)
public class AppTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void register() throws Exception {
        App app= new App();
        Assert.assertEquals(app.authenticate("test","pass"),false);
        app.Register("test","password",0);
        Assert.assertEquals(app.authenticate("test","pass"),false);
    }



    @Test
    public void getType() throws Exception {
        App app= new App();
        Integer actual=0;
        Assert.assertEquals(app.getType("test"),actual);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(App.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
