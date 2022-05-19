const mostrarUser = () =>{
    let newUser = document.getElementById("indicador-user");
    newUser.textContent = "Usuario: " + localStorage.getItem('usuario');
}

mostrarUser();