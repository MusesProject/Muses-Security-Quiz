package ch.unige;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Employee.class, beanName = "employeeBean")
public class EmployeeBean {
}
