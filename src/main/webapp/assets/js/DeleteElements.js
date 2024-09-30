/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Clase para poder eliminar inputs de forma dinámica
 */

 class DeleteElements{
	 constructor(){
		 
	 }
	 
	 deleteInput(event){
		 let inputsAndTextAreas = document.querySelectorAll("input, textarea");
		 
		 if(inputsAndTextAreas.length > 10){
			 let input = inputsAndTextAreas[inputsAndTextAreas.length - 1];
			 input.parentNode.removeChild(input);
		 }else{
			 return;
		 }
	 }
 }