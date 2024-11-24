console.log("member.js!!");

// 1, Vue.jsで扱うデータを用意する
const userData = {
    appName: "ユーザー詳細",// アプリ名
    user:{
        user_id: "",
        name_last: "",
        name_first: "",
        user_role: ""
    },
    old_user:{
        user_id: "",
        name_last: "",
        name_first: "",
        user_role: ""
    },
    user_id: "",
    name_last: "",
    name_first: "",
    user_role: ""
}

// 2, Vue.jsの準備をする
const app = Vue.createApp({
    data(){
        return userData;// 扱うデータを指定する
    },
    created(){
        console.log("created!!");
        this.loadUser();// Load
    },
    methods:{
        loadUser(){
            console.log("load detail member!!");

            this.user_id = localStorage.getItem("user_id");

            // Axios
            const url = "/v1/user?userId="+this.user_id;
            axios.get(url).then(res=>{
                //console.log(res.data);
                const entries = Object.entries(res.data);
                entries.sort((a, b)=>{return a[0] < b[0];});
                this.user = Object.fromEntries(entries);

                this.user_id = this.user.user_id;
                this.name_last = this.user.name_last;
                this.name_first = this.user.name_first;
                this.user_role = this.user.user_role;

                this.old_user.user_id = this.user.user_id;
                this.old_user.name_last = this.user.name_last;
                this.old_user.name_first = this.user.name_first;
                this.old_user.user_role = this.user.user_role;
            }).catch(err=>{
                console.log(err);
            });
        },
        cancelUser(user_id){
            this.user_id = this.old_user.user_id;
            this.name_last = this.old_user.name_last;
            this.name_first = this.old_user.name_first;
            this.user_role = this.old_user.user_role;
        },
        updateUser(user_id){
            console.log("save!!");
            if(user_id == null) {
                alert("ユーザーが存在しない。");
                return;
            }
            // Target User
            const updateUser = {
                "user_id": this.user_id,
                "name_last": this.name_last,
                "name_first": this.name_first,
                "user_role": this.user_role
            };
            // Axios
            const url = "/v1/user";
            axios.put(url, updateUser).then(res=>{
                //console.log(res.data);
                this.loadUser();// Load
            }).catch(err=>{
                console.log(err);
            });
        },
    }
});
app.mount("#appMember");// 3, Vue.jsを起動する