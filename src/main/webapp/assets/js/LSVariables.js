/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 28/04/2023
 * @description Esta clase es EXTRA, es para que tenga el array con los id de los input y textarea
 */

class LSVariables{
    constructor(){
        this.NAME_ITEMS = [];
    }
    
    llenarArrayDOM(elementsDOM){
		 for(let i=0; i<elementsDOM.length; i++){
		 	this.NAME_ITEMS.push(elementsDOM[i].id);
		 }
		 
		 return this.NAME_ITEMS;
	}
}