export const TitleButtonMixin = {

    created() { 
        console.log("test");
    },
    
  data: () => ({
    email: "",
    emailValid: false,
    name: "",
    nameValid : false,
    title: "",
    titleValid : false ,
    numerateur :0,
    dateValid : false,
    dates : []
    
  }),

  methods: {
    updateCount() {
    this.numerateur = 0 ;
      var arr = [this.emailValid, this.titleValid, this.nameValid, this.dateValid];
      arr.forEach((value) =>{
        if (value ){
          this.numerateur++;
        } 
      }
      );
    },
    validateTitle() {
      this.titleValid =  false;
      var result =  this.required(this.title);
      if (result===true) {
         result = this.counter(this.title);
        if ( result===true) {
        result = this.counterMin(this.title); 
          if (result ===true) {
            this.titleValid =  true;
          }
      }
      }
      this.updateCount();
      return result;   
      }
    ,
    validateName() {
       this.nameValid =  false;
      var result =  this.required(this.name);
       console.log(result);
      if (result === true) {
         console.log(result);
         result = this.counter(this.name);
      if (result ===true) {
         console.log(result);
         result = this.counterMin(this.name);
         if (result ===true ){
        this.nameValid =  true;
        }
        }
      }
        console.log(result);
        console.log("name : " +this.name)
        console.log("counter : " + this.counterMin(this.name))
       this.updateCount();
      return result;
      
      },
      validateDate() {
        var result  =  "dates is required";
        console.log(this.dates);
           result = this.required(this.dates);

           if (result === true ){
               this.dateValid = true;
           }
           this.updateCount();
        return result;
      }
    ,
    validateEmail() {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      var result = "Invalid e-mail.";
       this.emailValid = false;
      result =  this.required(this.email);
      if (result ===true ) {
        result =  pattern.test(this.email)

        if (result === true){
        this.emailValid = true ;
       
       }
      }
       this.updateCount();
      return result;
    },
    required(value) {
      return !!value || "Required.";
    },
    counter( value) {
     return value.length <= 20 || "Max 20 characters"
     },
    counterMin( value) {
     return value.length > 2 || "Min 2 characters"
     }
  }
};
