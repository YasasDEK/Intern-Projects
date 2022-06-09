## How to set up Keycloak 

As the keycloak instance is deployed using docker, all the configurations are in [docker-compose-dev-env](../docker/docker-compose-dev-env.yml).

## Users and Roles

1. Users are authenticated via Google.
2. When a user logs in for the first time, the user will not have any roles assigned and is not authorized to access the web portal.
3. To assign a role to a user,
   * Go to [hview.hsenidmobile.com/auth/](https://hview.hsenidmobile.com/auth/) and go to Admin console.
   * Go to user roles and check the available roles.
   * Go to users and select the user, and go to role mappings.
   * Select a role from the available roles and add selected.
   