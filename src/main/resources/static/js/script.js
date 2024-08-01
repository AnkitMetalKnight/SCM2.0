console.log("Script loaded");


//change theme work
let currentTheme = getTheme();
//initial -->

document.addEventListener("DOMContentLoaded",() => {
    changeTheme()
});

//TODO:
function changeTheme()
{
    
    //set to webpage
    changePageTheme(currentTheme,currentTheme);
    
    //set the listener  to change theme button
    const changeThemeButton =  document.querySelector("#theme_change_button");
    
    changeThemeButton.addEventListener("click",(event) => {
        let oldTheme = currentTheme;
        console.log("change theme button clicked");
        if(currentTheme === "dark")
        {
            //theme ko light krna h
            currentTheme="light";
        }
        else
        {
            //theme ko dark krna h
            currentTheme = "dark";
        }
            changePageTheme(currentTheme,oldTheme);
    });

}

//set theme to local storage
function setTheme(theme)
{
    localStorage.setItem("theme",theme);

}

//get theme from local storage
function getTheme()
{
    let theme = localStorage.getItem("theme");
    if(theme)
    return theme;
    else return "light";
}

function changePageTheme(theme,oldTheme)
{
//localstorage me update krna h

setTheme(currentTheme);
//remove the current theme

document.querySelector('html').classList.remove(oldTheme);

//set the current theme
if(oldTheme)
{
    document.querySelector('html').classList.add(theme);
}

//change the text of current button
document.querySelector("#theme_change_button").querySelector("span").textContent = theme=="light" ? "Dark" : "Light";
}

//change page theme work

