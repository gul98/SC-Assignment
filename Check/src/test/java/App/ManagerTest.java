package App;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by gulza on 22/05/2017.
 */
@RunWith(Arquillian.class)
public class ManagerTest {
    @Test
    public void deleteEmployee() throws Exception {

        Manager manager= new Manager();
        manager.addEmployee("test1","pass");
        App app= new App();
        Assert.assertEquals(app.authenticate("test1","pass"),true);
        manager.deleteEmployee("test1");
        Assert.assertEquals(app.authenticate("test1","pass"),false);

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Manager.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
