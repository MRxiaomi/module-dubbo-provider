apiVersion: apps/v1
kind: Deployment
metadata:
  name: dubbo-provider
spec:
  replicas: 4
  selector:
    matchLabels:
      app: dubbo-provider
  strategy:
    type: RollingUpdate
  rollingUpdate:
    maxUnavailable: 50%
    maxSurge: 50%
  template:
    metadata:
      labels:
        app: dubbo-provider
    spec:
      containers:
      - name: dubbo-provider-service
        image: liuyumeng/dubbo-provider:<BUILD_TAG>
        ports:
        - containerPort: 8080

