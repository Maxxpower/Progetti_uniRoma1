import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateValidator")
public class FutureDateValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		Date todayDate= new Date(); //data odierna
		Date selectedDate=(Date)arg2;
		
		
		if(selectedDate.after(todayDate)){
			
			
			
			FacesMessage msg= new FacesMessage("Non puoi selezionare una data nel futuro!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		

	}

}
