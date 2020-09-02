export const TitleButtonMixin = {

  data: () => ({
    email: "",
    emailValid: false,
    name: "",
    nameValid: false,
    title: "",
    titleValid: false,
    numerateur: 0,
    dateValid: false,
    dates: [],
    selectedDays: [],
  }),

  methods: {
    updateCount() {
      this.numerateur = 0;
      var arr = [
        this.emailValid,
        this.titleValid,
        this.nameValid,
        this.dateValid,
      ];
      arr.forEach((value) => {
        if (value) {
          this.numerateur++;
        }
      });
    },
    validateTitle() {
      this.titleValid = false;
      var result = this.required(this.title);
      if (result === true) {
        result = this.counter(this.title);
        if (result === true) {
          result = this.counterMin(this.title);
          if (result === true) {
            this.titleValid = true;
          }
        }
      }
      this.updateCount();
      return result;
    },
    validateName() {
      this.nameValid = false;
      var result = this.required(this.name);
      console.log(result);
      if (result === true) {
       
        result = this.counter(this.name);
        if (result === true) {
          
          result = this.counterMin(this.name);
          if (result === true) {
            this.nameValid = true;
          }
        }
      }
      this.updateCount();
      return result;
    },
    validateDate() {
      this.dateValid = false;
      var result = "dates is required";
     
      result = this.selectedDays.length != 0;

      if (result === true) {
        this.dateValid = true;
      }
      this.updateCount();
      return result;
    },
    validateEmail() {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      var result = "Invalid e-mail.";
      this.emailValid = false;
      result = this.required(this.email);
      if (result === true) {
        result = pattern.test(this.email);

        if (result === true) {
          this.emailValid = true;
        }
      }
      this.updateCount();
      return result;
    },
    required(value) {
      return !!value || "Required.";
    },
    counter(value) {
      return value.length <= 20 || "Max 20 characters";
    },
    counterMin(value) {
      return value.length >= 2 || "Min 2 characters";
    },
  },
};
