function startTimeout() {
    this.timer = setTimeout(() => {
        this.handleTimeout();
    }, this.timeoutDuration);
}

function handleTimeout() {
    // 超时后的操作，例如跳转到登录页面
    // this.$router.push('/login');
    location.href = "./index.html";
}

function resetTimeout() {
    clearTimeout(this.timer);
    this.startTimeout();
}

function eventFunc() {
    window.addEventListener('mousemove', this.resetTimeout);
    window.addEventListener('keydown', this.resetTimeout);
}

function destroyFunc() {
    window.removeEventListener('mousemove', this.resetTimeout);
    window.removeEventListener('keydown', this.resetTimeout);
}