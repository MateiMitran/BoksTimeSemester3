import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const CoachRoute = ({ component: Component, user, ...rest }) => {
  return (
    <Route {...rest} render={
      props => {
        if (localStorage.getItem('userRole')==="ROLE_COACH" || localStorage.getItem('userRole')==="ROLE_ADMIN" ) {
          return <Component {...rest} {...props} />
        } else {
          return <Redirect to={
            {
              pathname: '/unauthorized',
              state: {
                from: props.location
              }
            }
          } />
        }
      }
    } />
  )
}

export default CoachRoute;