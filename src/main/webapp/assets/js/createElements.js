/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Clase para poder crear inputs de forma dinámica
 */

 class createElements{
	 constructor(id, inputsURL){
		 this.id = id;
		 this.inputsURL = inputsURL;
	 }
	 
	 createInput(event){
		 let elementsDOM = document.querySelectorAll("input, textarea");
		 
		 let input = document.createElement("input");
		 input.id = `${this.id}${elementsDOM.length - 9}`;
		 input.className = "form-control";
		 input.type = "text";
		 input.placeholder = "example: https://pbs.twimg.com/profile_images/1623117815724081153/J7d04VDe_400x400.jpg";
		 
		 this.inputsURL.appendChild(input);
	 }
 }