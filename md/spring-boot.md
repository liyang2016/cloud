### spring profiles
>1. Mapped "{[/{name}-{profiles}.properties],methods=[GET]}"
>2. Mapped "{[/{name}-{profiles}.yml || /{name}-{profiles}.yaml],methods=[GET]}"
>3. Mapped "{[/{name}/{profiles}/{label:.*}],methods=[GET]}"
>4. Mapped "{[/{name}/{profiles:.*[^-].*}],methods=[GET]}"
>5. Mapped "{[/{label}/{name}-{profiles}.yml || /{label}/{name}-{profiles}.yaml],methods=[GET]}"
>6. Mapped "{[/{name}-{profiles}.json],methods=[GET]}"
>7. Mapped "{[/{label}/{name}-{profiles}.json],methods=[GET]}"
>8. Mapped "{[/{label}/{name}-{profiles}.properties],methods=[GET]}"
>9. Mapped "{[/{name}/{profile}/**],methods=[GET],params=[useDefaultLabel]}"
>10. Mapped "{[/{name}/{profile}/{label}/**],methods=[GET]}"
>11. Mapped "{[/{name}/{profile}/{label}/**],methods=[GET],produces=[application/octet-stream]}"

### bean加载规则
#### Autowired
根据类型查找合适的Bean
#### Resource
根据beanName从上下文中找到合适的Bean

### 设计模式
#### 简单工厂
BeanFactory
#### 单例模式
FactoryBean
#### 单例
#### 适配器
AOP通知功能
数据库连接驱动
#### 包装器
类名中还有Wrapper
#### 观察者
ApplicationListener EventPublishingRunListener
#### 策略模式
