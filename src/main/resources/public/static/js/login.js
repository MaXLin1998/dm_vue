console.log("login.js!!");

// 1, Vue.jsで扱うデータを用意する
const myData = {
    // アプリ名
    appName: "ログイン",
    pros: ["email"],

    loginPara:{
        email: "",
        password: "",
    },
    token:"",
}

// 2, Vue.jsの準備をする
const app = Vue.createApp({
    data(){
        return myData;// 扱うデータを指定する
    },
    created(){
        console.log("created!!");
        // Load
        // this.loadUsers();
    },
    methods:{
        loginHandle(){
            const headers = {
                'Content-Type': 'application/x-www-form-urlencoded',
            }

            // Axios
            const url = "/v1/login";
            axios.post(url, this.loginPara).then(res=>{
                const entries = Object.entries(res.data);
                let rtnLogin =  Object.fromEntries(entries);

                if(rtnLogin.status === 0 && rtnLogin.token != undefined){
                    this.isLoging = true;
                    let token = rtnLogin.token;
                    setTimeout(()=>{
                        var object = {
                            value: token, timestamp: new Date().getTime()}
                        localStorage.setItem("tokenObj", JSON.stringify(object));
                        localStorage.setItem("user_id", rtnLogin.email);
                        this.axios.defaults.headers.common['Authorization'] = token
                        this.isLoging = false;
                    },3000)
                } else {
                    this.titleText = 'アカウントもしくはパスワードが違います。'
                }
            }).catch(err=>{
                console.log(err);
            });
        },

        setAuthInfo (){
            let tokenObj = JSON.parse(localStorage.getItem("tokenObj"))
            let expireDays = 1000 * 60 * 60 * 24 * 15;
            if (tokenObj) {
                let dateString = tokenObj.timestamp
                let dtNow = new Date().getTime();
                if (dtNow - dateString> expireDays)
                {
                    localStorage.removeItem('tokenObj')
                    delete this.axios.defaults.headers.common['Authorization']
                }
                let token = tokenObj.value
                if (token) {
                    axios.defaults.headers.common['Authorization'] = token
                }
            }
        },
    }
});
app.mount("#appLogin");// 3, Vue.jsを起動する