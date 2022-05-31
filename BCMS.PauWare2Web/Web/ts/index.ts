import { CreatElt, HandleContent, RenderActions } from "./commons/common.js";
import FireMan from "./FireMan.js";
import Police from "./Police.js";

window.onload=()=>{
  const action=document.querySelector('.action');
  const title=document.querySelector('.content-title');
  const content=document.querySelector('.content');
  
  const contentPolice=document.querySelector('.police');
  const contentfireman=document.querySelector('.pompier');
  
  const actionsTodo=document.querySelector('.actionsTodo'); //ul des actions  
  
  let policeMan:Police=new Police();
  let fireMan:FireMan=new FireMan();

  const msg=(name:string)=>{
    const success=CreatElt('div');
    success.classList.add('success');
    success.innerHTML=name+" connecté avec sussès!"
    content?.appendChild(success);
  }

  let contentValue:Object=new Object();
  contentPolice?.addEventListener('click',(e:Event)=>{
    Object.assign(contentValue,{title: "Police Man Logged"});
    Object.assign(contentValue,{value: "Police"});
    
    if(actionsTodo && content){
      content.innerHTML="";
      actionsTodo.innerHTML="";
      if(actionsTodo.children.length<=0){
        RenderActions(policeMan,fireMan,actionsTodo,content,contentValue,'pm');
        const pm = document.querySelector(".pm");
        pm?.addEventListener('click',()=>{
          policeMan.set_islog(true);
          if(policeMan.get_islog()){
            pm?.setAttribute('disabled', 'true');
            if(action?.classList.contains('hide')){
                action.classList.remove('hide');
            }
            if (title && content){
              //HandleContent(policeMan,fireMan,title,content,contentValue);
              msg(policeMan.get_Name());
            }
          }
        })
        
      }
      
    }
  });

  contentfireman?.addEventListener('click',(e:Event)=>{
    
    Object.assign(contentValue,{title: "Fire Man Logged"});
    Object.assign(contentValue,{value: "Pompier"});
    if(actionsTodo && content){
      content.innerHTML="";
      actionsTodo.innerHTML="";
      if(actionsTodo.children.length<=0){
        RenderActions(policeMan,fireMan,actionsTodo,content,contentValue,'fm');
        const fm = document.querySelector(".fm");
        fm?.addEventListener('click',()=>{
          
      
          fireMan.set_islog(true);
          fireMan.set_islog(true);
          if(fireMan.get_islog()){
            fm?.setAttribute('disabled', 'true');
            if(action?.classList.contains('hide')){
                action.classList.remove('hide');
            }
            if (title && content)
             // HandleContent(policeMan,fireMan, title,content,contentValue);
             msg(fireMan.get_Name());
          }
          
        })  
      }
    }
  });
  
}


