/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 30/04/2023
 * @description Esta clase guardará por medio del LocalStorage los datos en caso de actualizar y no enviar
 */

 class InfoPersistence{
	 constructor(arrayDOM){
		this.arrayDOM = arrayDOM;
        this.PERSISTENCE = "persistence";
        this.variables = [];
    }

    verification(){
		
		let elementsDOM = document.querySelectorAll("input, textarea");
		
		let IDs = this.arrayDOM.llenarArrayDOM(elementsDOM);
		this.variables = IDs;

        if(window.localStorage.getItem(this.PERSISTENCE) != null){
            for(let i=0; i<elementsDOM.length; i++){
                elementsDOM[i].value = window.localStorage.getItem(IDs[i]);
            }
        }else{
            window.localStorage.setItem(this.PERSISTENCE, true);
            for(let i=0; i<elementsDOM.length; i++){
                window.localStorage.setItem(IDs[i], "");
            }
        }
    }

    persistenceValue(event){

        let inputOrTextArea = event.target;
        
        let elementsDOM = document.querySelectorAll("input, textarea");

        for(let i=0; i<elementsDOM.length; i++){
            if(inputOrTextArea.id == this.variables[i]){
                window.localStorage.setItem(this.variables[i], inputOrTextArea.value);
            }
        }

    }
 }