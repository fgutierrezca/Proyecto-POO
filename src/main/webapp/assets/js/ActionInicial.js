/**
 * @author jose.inestroza@unah.edu.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase por medio de comunicación asíncrona llamará un service que controlará el análisis inicial del modelo de datos
 */

 class ActionInicial{
	 
 	constructor(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual){
		 this.mostrarReg = mostrarReg;
		 this.totalReg = totalReg;
		 this.totalComments = totalComments;
		 this.totalShares = totalShares;
		 this.totalLikes = totalLikes;
		 this.totalVisual = totalVisual;
	 }
	 
	 /**
	  * @co-author fgutierrezc@unah.hn
	  * @description Se modificó este método para que generará las breves descripciones de los registros válidos
	  */
	 
	 processRequest(event){
		let xhr = event.target;
		
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				try{
					let response = JSON.parse(xhr.responseText);
					if(response.status){
						let json = response.services;
						for(let item of json){
							 let div = document.createElement("div");
							 div.id = item.identificador;
							 div.className = "text-bg-light p-3 m-3";
							 
							 let h1 = document.createElement("h1");
							 h1.className = "text-center";
							 h1.innerHTML = `Registro #${item.identificador}`;
							 
							 let p = document.createElement("p");
							 p.innerHTML = `<b>Nombre del usuario</b>: ${item.Nombre}<br><b>Cuenta del usuario</b>: ${item.Cuenta}<br>
							 				<b>Contenido del twitt</b>: ${item.Contenido}`;
							 let button = document.createElement("A");
							 button.id = item.identificador;
							 button.innerText = "Ver descripción";
							 button.className = "btn btn-secondary";
							 
							 div.appendChild(h1);
							 div.appendChild(p);
							 div.appendChild(button);
							 
							 this.mostrarReg.appendChild(div);
							 
							 let description = new ActionDescription(button);
							 button.addEventListener("click", description.send.bind(description));
						}
						
						this.totalReg.innerHTML = response.TotalTwitts;
						this.totalReg.value = response.TotalTwitts;
						this.totalComments.innerHTML = response.TotalComments;
						this.totalShares.innerHTML = response.TotalShares;
						this.totalLikes.innerHTML = response.TotalLikes;
						this.totalVisual.innerHTML = response.TotalVisual;
						
					}else{
						
					}
				}catch(ex){
					
				}
			}else{
				
			}
		}else{
			
		}
	}
	
	send(event){
		
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "modules/controllers/serviceInicial.jsp", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = this.processRequest.bind(this);
				
		xhr.send();
		
	}
	
}	