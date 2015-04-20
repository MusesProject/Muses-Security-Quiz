package musesproject.eu;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = UserProfile.class, beanName = "userProfileBean")
public class UserProfileBean {
}
