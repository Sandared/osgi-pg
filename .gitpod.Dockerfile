FROM gitpod/workspace-full
                    
USER gitpod

# Install custom tools, runtime, etc. using apt-get
# For example, the command below would install "bastet" - a command line tetris clone:
#
# RUN sudo apt-get -q update && #     sudo apt-get install -yq bastet && #     sudo rm -rf /var/lib/apt/lists/*
#
# More information: https://www.gitpod.io/docs/42_config_docker/

# Download Maven completion for bash and add some aliases
# TODO: Maybe enhance to also offer auto-completion for enroute Maven plugins
RUN wget -O .mvn_bash_completion https://raw.githubusercontent.com/natros/maven-bash-completion/master/bash_completion.bash \
&& echo 'alias run="java -jar "' >> ~/.bashrc \
&& echo 'alias debug="java -jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y "' >> ~/.bashrc \
&& echo 'source ~/.mvn_bash_completion' >> ~/.bashrc \

# Give back control

USER root
