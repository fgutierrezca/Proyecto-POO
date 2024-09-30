<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="author" content="fgutierrezc@unah.hn" />
		<meta name="version" content="0.1.0" />
		<meta name="date" content="02/05/2023" />
		<meta name="description" content="Página de registro manual de twitts" />
		
		<title>Vista Proyecto</title>
		
	    <link rel="shortcut icon" href="assets/images/logo.png">
	    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
	    <link rel="stylesheet" href="assets/css/style.css">
	</head>
	
	<body class="text-bg-light">
		<section class="container-fluid row justify-content-start shadow m-1 p-1 mb-2 bg-white rounded align-items-center">
	        <div class="col-3">
	            <span class="badge text-bg-light">Registro de twitts - Franklin Gutiérrez</span>
	        </div>
	        
	        <div class="col-2">
	        	<!-- Probablemente se cambie este  -->
	            <select class="form-select" aria-label="Default select example">
	                <option selected>Inicio</option>
	                <option value="1" id="agregarReg">Crear registro</option>
	                <option value="2" id="limpiarMD">Limpiar modelo de datos</option>
	              </select>
	        </div>
	
	        <div class="col-2">
	            <select class="form-select" aria-label="Default select example">
	                <option selected>Acerca de</option>
	                <option value="1" id="autorInfo">Autor</option>
	              </select>
	        </div>
	    </section>
	
	    <section class="container-fluid justify-content-center">
	        <div id="mostrarReg" class="shadow m-2 bg-white" style="overflow-y: scroll">
	            <div class="text-center p-2 m-2 bg-light">
	                <h3 class="text-center">Listado de registros actuales en el modelo de datos</h3>
	            </div>
	        </div>
			<div id="datosEsp" class="m-2">
	            <div class="text-bg-primary p-2 m-2">
	                <b>Total Registros</b>
	                <h1 class="text-center" id="totalReg">0</h1>
	            </div>
	            <div class="text-bg-success p-2 m-2">
	                <b>Registros con 100k likes</b>
	                <h1 class="text-center" id="totalLikes">0</h1>
	            </div>
	            <div class="text-bg-danger p-2 m-2">
	                <b>Registros con 100k shares</b>
	                <h1 class="text-center" id="totalShares">0</h1>
	            </div>
	            <div class="text-bg-secondary p-2 m-2">
	                <b>Registros con 100k comentarios</b>
	                <h1 class="text-center" id="totalComments">0</h1>
	            </div>
	            <div class="text-bg-dark p-2 m-2">
	                <b>Registros con 100k visualizaciones</b>
	                <h1 class="text-center" id="totalVisual">0</h1>
	            </div>
	        </div>
	    </section>
	    
	    <div id="eliminateModal" class="modal fade" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title">Alerta 🚨🚨🚨</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <p>¿Está seguro en limpiar el modelo de datos?</p>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
	                    <button type="button" class="btn btn-danger" id="cleanMD">Si, estoy seguro</button>
	                </div>
	            </div>
	        </div>
	    </div>
		
		<div id="createModal" class="modal fade modal-lg" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title">Creación de registros</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <div class="mb-3">
					    	<p class="form-p">Responsable (nombre completo)</p>
					    	<input type="text" class="form-control" id="nombreRes" placeholder="example: Franklin Gutiérrez">
						</div>
						<div class="mb-3">
						    <p class="form-p">Cuenta (email)</p>
						    <input type="email" class="form-control" id="emailRes" placeholder="name@example.com">
						</div>
						<div class="mb-3">
						    <p class="form-p">Fecha de creación del twitt</p>
						    <input type="date" class="form-control" id="fechaCre" placeholder="name@example.com">
						</div>
						<div class="mb-3">
						    <p class="form-p">Fecha de registro en este sistema</p>
						    <input type="date" class="form-control" id="fechaRes" placeholder="name@example.com">
						</div>
						<div class="mb-3">
						    <p class="form-p">Contenido</p>
						    <textarea class="form-control" id="contentRes" placeholder="máximo 256 caracteres"> </textarea>
						</div>
						<div class="mb-3">
						    <p class="form-p">Cantidad de comentarios</p>
						    <input type="text" class="form-control" id="commentsRes" placeholder="ingrese un número sin comas, puntos o espacios">
						</div>
						<div class="mb-3">
						    <p class="form-p">Cantidad de re-twitts</p>
						    <input type="text" class="form-control" id="retwittsRes" placeholder="ingrese un número sin comas, puntos o espacios">
						</div>
						<div class="mb-3">
						    <p class="form-p">Cantidad de likes</p>
						    <input type="text" class="form-control" id="likesRes" placeholder="ingrese un número sin comas, puntos o espacios">
						</div>
						<div class="mb-3">
						    <p class="form-p">Cantidad de visualizaciones</p>
						    <input type="text" class="form-control" id="visualRes" placeholder="ingrese un número sin comas, puntos o espacios">
						</div>
						<div class="mb-3" id="inputsURL">
						    <p class="form-p">Archivos multimedia por URL (Opcional)</p>
						    <input type="text" class="form-control" id="multimedia" placeholder="example: https://pbs.twimg.com/profile_images/1623117815724081153/J7d04VDe_400x400.jpg">
						</div>
						<button type="button" class="btn btn-info" id="aggURL">Agregar URL</button>
						<button type="button" class="btn btn-info" id="removeURL">Quitar URL</button>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	                    <button type="button" class="btn btn-primary" id="saveRegistro">Guardar registro</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <div id="autorModal" class="modal fade modal-lg" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title">Información del autor</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <p>
	                    	<b>Nombre: </b> Franklin Ariel Gutiérrez Castillo<br>
	                    	<b>Correo institucional: </b> fgutierrezc@unah.hn<br>
	                    	<b>Asignatura: </b> Programación Orientada a Objetos (POO) 1000 <br>
	                    	<b>Fecha: </b> 28 de abril del 2023<br>
	                    	<b>Docente: </b> Ing. José Inestroza<br>
	                    	<b>No. de cuenta: </b> 20211021383
	                    </p>
	                    <br>
	                    <p>
	                    	Sistema de registros de twitts de la plataforma Twitter mediante el ingreso manual de datos<br>
	                    	<br>El siguiente sistema esta hecho con java, javaScript, css, html y componentes de bootstrap.
	                    	El presente proyecto está basado en el paradigma de la programación orientada a objetos, 
	                    	el cual contiene varias clases de javaScript que hacen llamado a una página jsp por medio de
	                    	comunicación asíncrona (AJAX), las cuales reciben una respuesta de tipo JSON y genera una
	                    	respuesta visual en la vista de la página.
	                    </p>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar ventana</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <div id="descriptionModal" class="modal fade modal-xl" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title">Información del registro</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <p id="contentDescription"></p>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar ventana</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <script src="assets/js/bootstrap.bundle.min.js"></script>
	    
	    <script type="text/javascript" src="assets/js/LSVariables.js"></script>
	    <script type="text/javascript" src="assets/js/Validator.js"></script>
	    <script type="text/javascript" src="assets/js/ActionInicial.js"></script>
	    <script type="text/javascript" src="assets/js/ActionRegistration.js"></script>
	    <script type="text/javascript" src="assets/js/createElements.js"></script>
	    <script type="text/javascript" src="assets/js/DeleteElements.js"></script>
	    <script type="text/javascript" src="assets/js/ActionDescription.js"></script>
	    <script type="text/javascript" src="assets/js/ActionClean.js"></script>
	    <script type="text/javascript" src="assets/js/InfoPersistence.js"></script>
	    <script type="text/javascript" src="assets/js/ModalEncabezado.js"></script>
	    <script type="text/javascript" src="assets/js/main.js"></script>
	</body>
</html>