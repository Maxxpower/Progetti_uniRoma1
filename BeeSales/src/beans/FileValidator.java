package beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("fileValidator")
public class FileValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {

		// converto l'oggetto in input come oggetto di tipo part (file)
		Part fileInput = (Part) arg2;
		FacesMessage msg = null;

		if (fileInput == null) {

			msg = new FacesMessage("Selezionare un file!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		} else if (fileInput.getSize() >= 4096) {

			msg = new FacesMessage("Il file selezionato è troppo grande!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}

}
