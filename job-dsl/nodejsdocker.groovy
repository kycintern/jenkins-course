job('NodeJS Docker example') {
    scm {
        git('git://github.com/mrlinh9988/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('mrlinhdeptrai9988')
            node / gitConfigEmail('mrlinhdeptrai9988@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('mrlinhdeptrai9988/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub') // config credential dockerhub in jenkins
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
