import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAppUser, defaultValue } from 'app/shared/model/app-user.model';

export const ACTION_TYPES = {
  FETCH_APPUSER_LIST: 'appUser/FETCH_APPUSER_LIST',
  FETCH_APPUSER: 'appUser/FETCH_APPUSER',
  CREATE_APPUSER: 'appUser/CREATE_APPUSER',
  UPDATE_APPUSER: 'appUser/UPDATE_APPUSER',
  DELETE_APPUSER: 'appUser/DELETE_APPUSER',
  RESET: 'appUser/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAppUser>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AppUserState = Readonly<typeof initialState>;

// Reducer

export default (state: AppUserState = initialState, action): AppUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_APPUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_APPUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_APPUSER):
    case REQUEST(ACTION_TYPES.UPDATE_APPUSER):
    case REQUEST(ACTION_TYPES.DELETE_APPUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_APPUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_APPUSER):
    case FAILURE(ACTION_TYPES.CREATE_APPUSER):
    case FAILURE(ACTION_TYPES.UPDATE_APPUSER):
    case FAILURE(ACTION_TYPES.DELETE_APPUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_APPUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_APPUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_APPUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/app-users';

// Actions

export const getEntities: ICrudGetAllAction<IAppUser> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_APPUSER_LIST,
  payload: axios.get<IAppUser>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAppUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_APPUSER,
    payload: axios.get<IAppUser>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAppUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_APPUSER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAppUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_APPUSER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAppUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_APPUSER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
