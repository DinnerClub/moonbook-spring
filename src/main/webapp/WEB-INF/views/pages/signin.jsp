<div class="page-signin">

    <div class="signin-header">
        <div class="container text-center">
            <section class="logo">
                <a href="#/">{{main.brand}}</a>
            </section>
        </div>
    </div>

    <div class="signin-body">
        <div class="container">
            <div class="form-container">

                <section class="row signin-social text-center">
                    <a href="javascript:;" class="btn-twitter-round"><i class="fa fa-twitter"></i></a>
                    <div class="space"></div>
                    <a href="javascript:;" class="btn-facebook-round"><i class="fa fa-facebook"></i></a>
                    <div class="space"></div>
                    <a href="javascript:;" class="btn-google-plus-round"><i class="fa fa-google-plus"></i></a>
                </section>

                <span class="line-thru">OR</span>

                <form class="form-horizontal" action="/moonbook/views/pages/signin" method="post">
                    <fieldset>
                        <div class="form-group">
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email" name="username" 
                                       class="form-control"
                                       placeholder="Email"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </span>
                                <input type="password" name="password" 
                                       class="form-control"
                                       placeholder="password"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                        </div>
                        <div class="form-group">
                            <a type="submit" class="btn btn-primary btn-lg btn-block">Log in</a>
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Log in</button>
                        </div>
                    </fieldset>
                </form>

                <section>
                    <p class="text-center"><a href="javascript:;">Forgot your password?</a></p>
                    <p class="text-center text-muted text-small">Don't have an account yet? <a href="#/pages/signup">Sign up</a></p>
                </section>
                
            </div>
        </div>
    </div>

</div>