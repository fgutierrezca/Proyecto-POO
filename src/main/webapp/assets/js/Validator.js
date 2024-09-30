/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase hace la validación de la información ingresada en la inscripción del registro
 */

 class Validator{
	 
	 constructor(){
		 
	 }
	 
	 validateData(objectDOMs){
		 
		 if(objectDOMs[1].value.replace(/(\w\d?)+@(\w\d?)+\.(\w\d?)+/, "").length == 0){
			 if(objectDOMs[4].value.length >= 64 && objectDOMs[4].value.length <= 280){
			 	 if(objectDOMs[5].value.replace(/\d+/, "").length == 0){
					 if(objectDOMs[6].value.replace(/\d+/, "").length == 0){
					 	if(objectDOMs[7].value.replace(/\d+/, "").length == 0){
					 		if(objectDOMs[8].value.replace(/\d+/, "").length == 0){
					 			return true;
				 			}
				 		}
				 	 }
				 }
		 	 }
		 }
		 
		 return false;
	 }

 }