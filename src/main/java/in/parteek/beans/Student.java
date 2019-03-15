/**
 * 
 */
package in.parteek.beans;

import javax.persistence.Id;
import javax.persistence.Entity;

import lombok.*;

/**
 * Created on : 2019-03-14, 9:27:58 a.m.
 *
 * @author Parteek Dheri
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class Student {

	@Id
	private int id;
	private String name;
}
