/**
 * @author jose.inestroza@unah.edu.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase por medio de comunicación asíncrona llamará un service que controlará el ingreso de un nuevo registro
 */

 class ActionRegistration{
	 
	 constructor(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual){
		 this.mostrarReg = mostrarReg;
		 this.totalReg = totalReg;
		 this.totalComments = totalComments;
		 this.totalShares = totalShares;
		 this.totalLikes = totalLikes;
		 this.totalVisual = totalVisual;
		 
		 this.validator = new Validator();
	 }
	 
	 /**
	  * @co-author fgutierrezc@unah.hn
	  * @description Se modificó este método para que generará la breves descripció del registro recién ingresado si es válido
	  */
	 
	 processRequest(event){
		let xhr = event.target;
		
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				try{
					let response = JSON.parse(xhr.responseText);
					if(response.status){
						
						 let inputsAndTextAreas = document.querySelectorAll("input, textarea");
						
						 for(let i=0; i<inputsAndTextAreas.length; i++){
						 	inputsAndTextAreas[i].value = "";
						 }
						 
						 window.localStorage.clear();
						 
						 let div = document.createElement("div");
						 div.id = response.TotalTwitts;
						 div.className = "text-bg-light p-3 m-3";
						 
						 let h1 = document.createElement("h1");
						 h1.className = "text-center";
						 h1.innerHTML = `Registro #${response.TotalTwitts}`;
						 
						 let p = document.createElement("p");
						 p.innerHTML = `<b>Nombre del usuario</b>: ${response.Nombre}<br><b>Cuenta del usuario</b>: ${response.Cuenta}<br>
						 				<b>Contenido del twitt</b>: ${response.Contenido}`;
						 let button = document.createElement("A");
						 button.id = response.TotalTwitts;
						 button.innerText = "Ver descripción";
						 button.className = "btn btn-secondary";
						 
						 div.appendChild(h1);
						 div.appendChild(p);
						 div.appendChild(button);
						 
						 this.mostrarReg.appendChild(div);
						 
						 this.totalReg.innerHTML = response.TotalTwitts;
						 this.totalReg.value = response.TotalTwitts;
						 this.totalComments.innerHTML = response.TotalComments;
						 this.totalShares.innerHTML = response.TotalShares;
						 this.totalLikes.innerHTML = response.TotalLikes;
						 this.totalVisual.innerHTML = response.TotalVisual;
						 
						 for(let i=0; i<inputsAndTextAreas.length; i++){
						 	 if(i > 9){
								 let input = inputsAndTextAreas[i];
								 input.parentNode.removeChild(input);
							 }
						 }
						 
						 let description = new ActionDescription(button);
						 button.addEventListener("click", description.send.bind(description));
					}else{
						
					}
				}catch(ex){
					
				}
			}else{
				
			}
		}else{
			
		}
	}
	
	processInputsAndTextareas(array){
		let variables = [];
		
		for(let item of array){
			variables.push(`${item.id}=${item.value}`);
		}
		
		return variables.join("&");
	}
	
	send(event){
		
		let inputsAndTextAreas = document.querySelectorAll("input, textarea");
		
		if(this.validator.validateData(inputsAndTextAreas)){
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "modules/controllers/serviceCreate.jsp", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
			xhr.onreadystatechange = this.processRequest.bind(this);
			
			let variables = this.processInputsAndTextareas(inputsAndTextAreas);
					
			xhr.send(variables);
		}else{
			return;
		}
		
	}
 }