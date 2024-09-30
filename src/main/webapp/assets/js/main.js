/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 28/04/2023
 * @description Esta clase se encarga de hacer llamado a los objetos del DOM y las instancias de las clases .js
 */

 let modalCreate = new ModalEncabezado("createModal", "eliminateModal", "autorModal");
 
 /**
  * Opciones de las listas despegables que están en el encabezado
  */
 
 let agregarReg = document.querySelector("option#agregarReg");
 let limpiarMD = document.querySelector("option#limpiarMD");
 let autorInfo = document.querySelector("option#autorInfo");

 /**
  * Llamado a las ventanas modales del encabezado
  */

 agregarReg.addEventListener("click", modalCreate.showCreateModal.bind(modalCreate));
 limpiarMD.addEventListener("click", modalCreate.showEliminateModal.bind(modalCreate));
 autorInfo.addEventListener("click", modalCreate.showAutorModal.bind(modalCreate));
 
 /**
  * Aquí se hará la persistencia, por medio del evento change, para que cada vez que cambié guarde 
  */
 
 let inputsAndTextAreas = document.querySelectorAll("input, textarea");
 let aggURL = document.querySelector("button#aggURL");
 let removeURL = document.querySelector("button#removeURL");
 let inputsURL = document.querySelector("div#inputsURL");
 
 let arrayDOM = new LSVariables();

 let ls = new InfoPersistence(arrayDOM);

 ls.verification();

 for(let i=0; i<inputsAndTextAreas.length-1; i++){
 	inputsAndTextAreas[i].addEventListener("change", ls.persistenceValue.bind(ls));
 }
 
 /**
  * Los divs del cuerpo de la página
  */
 
 let mostrarReg = document.querySelector("div#mostrarReg");
 let totalReg = document.querySelector("h1#totalReg");
 let totalComments = document.querySelector("h1#totalComments");
 let totalShares = document.querySelector("h1#totalShares");
 let totalLikes = document.querySelector("h1#totalLikes");
 let totalVisual = document.querySelector("h1#totalVisual");
 
 /**
  * Inicialización
  */
  
 let inicicializacion = new ActionInicial(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual);
 inicicializacion.send();
 
 /**
  * Los botones que hacen acción en el encabezado, Guardar Registro - Limpiar Modelo
  */
 
 let cleanMD = document.querySelector("button#cleanMD");
 let saveRegistro = document.querySelector("button#saveRegistro");
 
 /**
  * Acción de los botones del encabezado, por medio del evento click
  */
 
 let createInputs = new createElements(inputsAndTextAreas[9].id, inputsURL);
 
 let deleteInputs = new DeleteElements();
 
 aggURL.addEventListener("click", createInputs.createInput.bind(createInputs));
 
 removeURL.addEventListener("click", deleteInputs.deleteInput.bind(deleteInputs));
  
 let actClean = new ActionClean(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual);
 cleanMD.addEventListener("click", actClean.send.bind(actClean));
 
 let actRegistration = new ActionRegistration(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual);
 saveRegistro.addEventListener("click", actRegistration.send.bind(actRegistration));
 
 
 
 