let usuarios=["Blas", "Jaime", "Ignacio"]
let contraseña="dentista";
let divAlertPass = document.createElement("div");
let divAlertUser = document.createElement("div");


const validarUsuario = () => {
    divAlertPass.innerHTML = "";
    divAlertUser.innerHTML = "";
    let userOK = 0;
    let user = document.getElementById("user").value;
    document.getElementById("user").value = "";
    let pass = document.getElementById("password").value;
    document.getElementById("password").value = "";
    for(let i=0;i < usuarios.length; i++){
        if(usuarios[i] == user){
            userOK = 1;
            localStorage.setItem('usuario',user);
        }
    }
    if((pass == contraseña) && (userOK==1)){
        window.location.href="./index.html";

    } else {
        let validacion = document.getElementById("validacion");
        if(pass != contraseña){
            divAlertPass.innerHTML = "<div id='alertaPass' class='alert alert-danger alerta' role='alert'> Contraseña Incorrecta </div>";
            validacion.insertAdjacentElement("afterend",divAlertPass);
        }
        if(userOK == 0){
            divAlertUser.innerHTML = "<div id='alertaUser' class='alert alert-danger alerta' role='alert'> Usuario no Registrado </div>";
            validacion.insertAdjacentElement("afterend",divAlertUser);
        }
    }
} 