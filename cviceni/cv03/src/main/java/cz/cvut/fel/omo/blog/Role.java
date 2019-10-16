package cz.cvut.fel.omo.blog;


import java.io.Serializable;



/**
 * @author Matej
 * @version 1.0
 * @created 13-øíj-2019 17:07:22
 */
public class Role implements Serializable{

	private final String name;

	public Role(String name){
            this.name = name;
	}

    public String getName() {
        return name;
    }
   
    public boolean equals(Role obj) {
        return obj.getName().equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}