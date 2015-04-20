package musesproject.eu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;





public class UserServiceImpl implements UserDetailsService {
	
	private Hashtable<String, User> users = new Hashtable<String, User>();
		 
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {    	
    	
    	users.putAll(refreshUsers());// = refreshUsers();

    	
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    	
    	users.put("xavier", new User("xavier", "titi", authorities));
    	users.put("jm", new User("jm", "seigneur", authorities));
    	users.put("jo", new User("jo", "guislain", authorities));
    	users.put("admin", new User("admin", "admin", authorities));
    	

    	if (users.containsKey(username)) {
        	return users.get(username);
        } else {
        	/*List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        	User newUser = new User(username, username, authorities);
        	//users.put(username, newUser);*/
        	throw new UsernameNotFoundException("User not found: " + username);
        }
    }
    
    public Hashtable<String, User> refreshUsers() {
    	Hashtable<String, User> usersHashtable = new Hashtable<String, User>();
    	List<musesproject.eu.Employee> usersList = musesproject.eu.Employee.findAllEmployees();
    	for (musesproject.eu.Employee employee : usersList) {
        	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    		
    			//authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    		usersHashtable.put(employee.getEmail(), new User(employee.getEmail(), employee.getPassword(), authorities));
		}
    	return usersHashtable;
    }
    
   
}